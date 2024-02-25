import { Time } from '@angular/common';
import { Component, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompetitionResp } from 'src/app/core/model/CompetitionResp';
import { Member } from 'src/app/core/model/Member';
import { UserResponseInfo } from 'src/app/core/model/UserResponseInfo';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';
import { CompetitionService } from 'src/app/core/service/competition.service';
import { MemberService } from 'src/app/core/service/member.service';

@Component({
  selector: 'app-competition-detail',
  templateUrl: './competition-detail.component.html',
  styleUrls: ['./competition-detail.component.css']
})
export class CompetitionDetailComponent {
  user:UserResponseInfo = {} as UserResponseInfo;
  membersLoading = false;
  alertType = '';
  alertMessage = '';
  showAlert = false;
  members:Member[] = [];
  competition: CompetitionResp[] = [];
  loading = true;
  competitionId: string = '';
  search='';
  showMembers = false;
  constructor(
    private competitionService:CompetitionService,
    private route: ActivatedRoute,
    private memberService:MemberService,
    private elementRef: ElementRef,
     private renderer: Renderer2,
     private tokenStorageService:TokenStorageService
  ) { }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.getUserInfo();
      this.competitionId = params['code'];
      this.getCompetition();
      if(this.user.role=='MANAGER' || this.user.role=='JURY'){
      this.getMembers();
      }
    }
    );
  }
  getCompetition(){
    if(this.user.role=='MANAGER' || this.user.role=='JURY'){
        this.competitionService.getCompetition(this.competitionId).subscribe(
          (data)=>{
            this.competition.push(data.data);
            this.checkLoadingComplete();
          },
          (error:any)=>{
            console.log(error);
            this.loading = false;
          }
        );
    }else{
      this.competitionService.getMyCompetitionByCode(this.competitionId).subscribe(
        (data)=>{
          this.competition.push(data.data);
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
    }
    
  }
  checkLoadingComplete(){
    if(this.competition.length > 0 ){
      this.loading = false;
    }
  }
  toTime(time: string): Time {
    let t = time.toString().split(':');
    return { hours: parseInt(t[0]), minutes: parseInt(t[1]) };
  }
  getMembers(page:number=0){
    this.memberService.getMembersPaginatedSearch(null,null,'').subscribe(
      (data)=>{
        this.members=data.data;

      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  searchMembers(search:string=''){
    this.memberService.getMembersPaginatedSearch(null,null,search).subscribe(
      (data)=>{
        this.members=data.data;
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  showMembersList(){
    this.showMembers = true;
    const element = this.elementRef.nativeElement.querySelector("#addmember");
    if (element) {
      this.renderer.setProperty(element, 'scrollTop', 0);
      element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
  addMember(number:string){
    this.membersLoading = true;
    this.competitionService.addMemberToCompetition(this.competitionId,number).subscribe(
      (data)=>{
        this.membersLoading = false;
        this.competition[0].rankings.push(data.data);
        const element = this.elementRef.nativeElement.querySelector("#competition");
        if (element) {
          this.renderer.setProperty(element, 'scrollButtom', 0);
          element.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
        this.showAlertMessage('Member added successfully','success');
      },
      (error:any)=>{
        this.membersLoading = false;
        console.log(error);
        this.showAlertMessage(error.error.message,'danger');
      }
    );
  }
  showAlertMessage(message:string, type:string){
    this.alertType = type;
    this.alertMessage = message;
    this.showAlert = true;
  }
  handleAlertClose(){
    this.showAlert = false;
  }
  getUserInfo(){
    this.user = this.tokenStorageService.getUser();
  }
}
