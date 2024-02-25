import { Time } from '@angular/common';
import { Component } from '@angular/core';
import { get } from 'lodash';
import { CompetitionResp } from 'src/app/core/model/CompetitionResp';
import { FishResp } from 'src/app/core/model/FishResp';
import { UserResponseInfo } from 'src/app/core/model/UserResponseInfo';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';
import { CompetitionService } from 'src/app/core/service/competition.service';
import { FishService } from 'src/app/core/service/fish.service';
import { LevelService } from 'src/app/core/service/level.service';
import { MemberService } from 'src/app/core/service/member.service';
import { RankingService } from 'src/app/core/service/ranking.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  user:UserResponseInfo = {} as UserResponseInfo;
  huntingLoading = false;
  alertMessage = '';
  showAlert = false;
  alertType = '';
  addHunting = false;
  count_competitions = -1;
  count_members = -1;
  count_fishes = -1;
  count_levels = -1;
  loading = true; 
  rankingRefresh = false;
  competitions: CompetitionResp[] = [];
  competition: CompetitionResp[] = [];
  fishes: FishResp[] = [];
  ranking = {
    competition_code:'',
    member_number: 0,
    weight: 0,
    fish_id:0
  };
  constructor(private competitionService:CompetitionService,
    private memberService:MemberService,
    private fishService:FishService,
    private levelService:LevelService,
    private rankingService:RankingService,
    private tokenStorageService:TokenStorageService
    ) { }
  ngOnInit() {
    this.getUserInfo();
    if(this.user.role=='MANAGER' || this.user.role=='JURY'){
      this.getcompititioncount();
      this.getmembercount();
      this.getfishcount();
      this.getlevelcount();
      this.getCompetitions();
      this.getFishes();
      this.getCompetitionToday();
    }else{
      this.getMyCompetitions();
      this.getMyTodayCompetition();
    }
  }

  getcompititioncount(){
    this.competitionService.getcount().subscribe(
      (data)=>{
      this.count_competitions=data.data;
      this.checkLoadingComplete();
    },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  getmembercount(){
    this.memberService.getcount().subscribe(
      (data)=>{
      this.count_members=data.data;
      this.checkLoadingComplete();
      },
      (error:any)=>{ 
        console.log(error);
      }
    );
  }
  getfishcount(){
    this.fishService.getcount().subscribe(
      (data)=>{
      this.count_fishes=data.data;
      this.checkLoadingComplete();
      },
      (error:any)=>{
        console.log(error);
        
      }
    );
  }
  getlevelcount(){
    this.levelService.getcount().subscribe(
      (data)=>{
      this.count_levels=data.data;
      this.checkLoadingComplete();
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  checkLoadingComplete() {
    if (
      this.count_competitions != -1 &&
      this.count_members != -1 &&
      this.count_fishes != -1 &&
      this.count_levels != -1
    ) {
      this.loading = false;
    }else if(this.user.role=="ADHERENT"){
      this.loading = false;
    }
  }
  getCompetitionToday(){
    this.competitionService.getTodayCompetition().subscribe(
      (data)=>{
        this.competition=data.data;
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  getFishes(){
    this.fishService.getFishes().subscribe(
      (data)=>{
        this.fishes=data.data;
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  toTime(time: string): Time {
    let t = time.toString().split(':');
    return { hours: parseInt(t[0]), minutes: parseInt(t[1]) };
  }
  refreshRankings(){
    this.rankingRefresh=true;
      this.rankingService.getRankingsOfCompetition(this.competition[0].code).subscribe(
        (data)=>{
          this.competition[0].rankings=data.data;
          this.rankingRefresh=false;
        },
        (error:any)=>{
          this.showAlertMessage(error.error.message,'danger');
          this.rankingRefresh=false;
        }
      );
  }
  getCompetitions(){
    this.competitionService.getCompetitions().subscribe(
      (data)=>{
        this.competitions=data.data;
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  getMyCompetitions(){
    this.competitionService.getMyCompetitions().subscribe(
      (data)=>{
        this.competitions=data.data;
        this.checkLoadingComplete();
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  getMyTodayCompetition(){
    this.competitionService.getMyTodayCompetition().subscribe(
      (data)=>{
        this.competition=data.data;
        this.checkLoadingComplete();
      },
      (error:any)=>{
        console.log(error);
      }
    );
  }
  showAddHunting(){
    this.addHunting=true;
  }
  addHuntingToComp(){
    if(!this.validateHunting()){
      return;
    }
    this.huntingLoading=true;
    this.ranking.competition_code=this.competition[0].code;
    this.competitionService.addHuntingToCompetition(this.ranking).subscribe(
      (data)=>{
        this.huntingLoading=false;
        this.addHunting=false;
        this.showAlertMessage('Hunting added successfully','success');
        this.ranking.fish_id=0;
        this.ranking.member_number=0;
        this.ranking.weight=0;
        this.refreshRankings();
      },
      (error:any)=>{
        this.huntingLoading=false;
        console.log(error);
        this.showAlertMessage(error.error.message,'danger');  
      }
    );
  }
  validateHunting(){
    if(this.ranking.member_number==0){
      this.showAlertMessage('Please select a member','warning');
      return false;
    }
    if(this.ranking.fish_id==0){
      this.showAlertMessage('Please select a fish','warning');
      return false;
    }
    if(this.ranking.weight==0){
      this.showAlertMessage('Please enter weight','warning');
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
  getUserInfo(){
    this.user = this.tokenStorageService.getUser();
  }

}
