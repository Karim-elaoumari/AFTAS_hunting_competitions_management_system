import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CompetitionResp } from 'src/app/core/model/CompetitionResp';
import { CompetitionService } from 'src/app/core/service/competition.service';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css']
})
export class AddCompetitionComponent {
  competitionLoading = false;
  alertType: string = '';
  alertMessage: string = '';
  showAlert: boolean = false;
  competition: CompetitionResp = {} as CompetitionResp;
  constructor(
    private competitionService:CompetitionService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }
  ngOnInit(): void {

  }
  addCompetition(){
    this.competitionLoading = true;
    console.log(this.competition);
   if(this.validate()){
    this.competitionService.addCompetition(this.competition).subscribe(
      (data)=>{
        this.router.navigate(['/competitions/'+data.data.code]);
        this.competitionLoading = false;
      },
      (error:any)=>{
        this.showAlertMessage("Error creating competition "+error.error.message,"danger");
        this.competitionLoading = false;
      }
    );
   }
    else{
      this.competitionLoading = false;
    }
  }
  validate(){ 
    if(this.competition.location==''
      || this.competition.amount==null
      || this.competition.date==null
      || this.competition.start_time==null
      || this.competition.end_time==null
      || this.competition.max_participants==null
      || this.competition.description==''
      ){
        this.showAlertMessage("Please fill all the fields","warning");
      return false;
      }
    if(this.competition.start_time>this.competition.end_time){
      this.showAlertMessage("Start time cannot be greater than end time","warning");
      return  false;
    }
    if(this.competition.amount<0){
      
      return false;
    }
    if(this.competition.max_participants<=0){
      this.showAlertMessage("Max participants cannot be negative or zero","warning");
      return false;
    }
    if(new Date(this.competition.date)<new Date()){
      this.showAlertMessage("Date cannot be in the past","warning");
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
