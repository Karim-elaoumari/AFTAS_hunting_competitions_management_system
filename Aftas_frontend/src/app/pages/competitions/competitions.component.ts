import { Component } from '@angular/core';
import { CompetitionResp } from 'src/app/core/model/CompetitionResp';
import { CompetitionService } from 'src/app/core/service/competition.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent {
    count_competitions = -1;
    competitions: CompetitionResp[] = [];
    loading = true;
    page= 0;
    size= 5;
    search='';
  
    constructor(
      private competitionService:CompetitionService,
      private route: ActivatedRoute,
      private router: Router,
      private tokenStorageService:TokenStorageService
      ) { }
  
    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        this.page = +params['page'] || 0;
        this.size = +params['size'] || 5; 
      });
      this.getCompetitions(this.page);
      this.getcompititioncount();


    }
    getCompetitions(page:number=0){
      let user  = this.tokenStorageService.getUser();
      if(user!=null && user!=undefined){
        if(user.role=='MANAGER' || user.role=='JURY'){
          this.getAllCompetitions(page);
        }else{
          this.getMyCompetitions(page);
        }
      }
    }
    searchCompetitions(search:string=''){
      
      this.page=0;
      this.competitionService.getCompetitionsPaginatedSearch(this.page,this.size,search).subscribe(
        (data)=>{
          this.competitions=data.data;
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
      this.setQueryInUrl();
    }
    getcompititioncount(){
      this.competitionService.getcount().subscribe(
        (data)=>{
        this.count_competitions=data.data;
        this.checkLoadingComplete();
      },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
    }
    checkLoadingComplete() {
      if (
        this.competitions.length > 0
      ){
        this.loading = false;
      }
    }
    viewCompetition(code:string){
      this.router.navigate(['/competitions/'+code]);
    }
    setQueryInUrl(){
      let query:string = '';
      if(this.search!=''){
        query='?page='+this.page+'&size='+this.size+'&search='+this.search;
      }
      else{
        query='?page='+this.page+'&size='+this.size;
      }
      this.router.navigate([], { queryParams: { page: this.page,size:this.size,search:this.search } });
     
    }
    getAllCompetitions(page:number){
      this.page=page;
      this.competitionService.getCompetitionsPaginatedSearch(page,this.size,'').subscribe(
        (data)=>{
          this.competitions=data.data;
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
      this.setQueryInUrl();
    }
    getMyCompetitions(page:number){
      this.page=page;
      this.competitionService.getMyCompetitionsPaginatedSearch(page,this.size,'').subscribe(
        (data)=>{
          this.competitions=data.data;
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
      this.setQueryInUrl();
    }


}
