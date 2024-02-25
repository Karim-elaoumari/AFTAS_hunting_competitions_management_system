import { Component } from '@angular/core';
import { Member } from 'src/app/core/model/Member';
import { UserResponseInfo } from 'src/app/core/model/UserResponseInfo';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';
import { MemberService } from 'src/app/core/service/member.service';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.css']
})
export class AddMemberComponent {
    memberLoading = false;
    alertType: string = '';
    alertMessage: string = '';
    showAlert: boolean = false;
    user:UserResponseInfo = {} as UserResponseInfo;
    memberRole: string = '';
    member: Member= {} as Member;
    constructor(
      private memberService:MemberService,
      private tokenStorageService:TokenStorageService
    ) { }
  
    ngOnInit(): void {
      this.getUserInfo();
    }
    addAdherent(){
      console.log(this.member);
      if(!this.validate()){
        return;
      }
      this.memberLoading = true;
      this.memberService.addMember(this.member).subscribe(
        (data)=>{
          this.memberLoading = false;
          this.showAlertMessage("Member created successfully Password:"+data.data.password,"success");
        },
        (error:any)=>{
          this.memberLoading = false;
          this.showAlertMessage("Error creating member "+error.error.message,"danger");
        }
      );
    }
    addMember(){
      if(this.memberRole=='JURY'){
        this.addJury();
      }else{
        this.addAdherent();
      }
    }
    addJury(){
      if(!this.validate()){
        return;
      }
      this.memberLoading = true;
      this.memberService.addJury(this.member).subscribe(
        (data)=>{
          this.memberLoading = false;
          this.showAlertMessage("Jury created successfully Password:"+data.data.password,"success");
        },
        (error:any)=>{
          this.memberLoading = false;
          this.showAlertMessage("Error creating jury "+error.error.message,"danger");
        }
      );
    }

    getUserInfo(){
      this.user = this.tokenStorageService.getUser();
    }
    validate(){
      if(this.member.first_name==''
        || this.member.last_name==''
        || this.member.nationality==''
        || this.member.identity_document_type==null
        || this.member.identity_number==null
        || this.member.number==null
        ){
          this.showAlertMessage("Please fill all the fields","warning");
        return false;
        }
      return true;  
    }
    showAlertMessage(message:string, type:string){ 
      this.alertType = type;
      this.alertMessage = message;
      this.showAlert = true;
    }
    handleAlertClose(){
      this.showAlert = false;
    }



}
