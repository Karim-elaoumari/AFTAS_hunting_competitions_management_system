import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { query } from '@angular/animations';
import { env } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly api:string = env.api;
  constructor(private http:HttpClient) {

  }
  public getcount():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/competitions/count");
  }
public getTodayCompetition(): Observable<any> {
  let today = '';
  let dd = new Date().getDate().toString().padStart(2, '0');
  let mm = (new Date().getMonth() + 1).toString().padStart(2, '0');
  let yyyy = new Date().getFullYear();
  today = `${yyyy}-${mm}-${dd}`;
  return this.http.get<any>(`${this.api}/api/v1/competitions?date=${today}`);
}
  public getCompetitions():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/competitions");
  }
  public getMyCompetitions():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/rankings/my-competitions");
  }
  public getMyTodayCompetition():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/rankings/my-today-competitions");
  }
  public getCompetition(code:string):Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/competitions/"+code);
  }
  public getMyCompetitionByCode(code:string):Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/competitions/my-competition/"+code);
  }

  public getCompetitionsPaginatedSearch(page:number=0,size:number=15,search:string):Observable<any>{
    let query:string = '';
    if(search!=''){
      query='?page='+page+'&size='+size+'&search='+search;
    }
    else{
      query='?page='+page+'&size='+size;
    }
    return this.http.get<any>(this.api+"/api/v1/competitions"+query);
  }
  public addCompetition(competition:any):Observable<any>{
    return this.http.post<any>(this.api+"/api/v1/competitions",competition);
  }
  public addMemberToCompetition(competitionId:string,memberId:string):Observable<any>{
    let ranking  =
      {
        member_number:memberId ,
        competition_code: competitionId
     };
    return this.http.post<any>(this.api+"/api/v1/rankings",ranking);
  }
  public addHuntingToCompetition(ranking:any):Observable<any>{
    return this.http.post<any>(this.api+"/api/v1/huntings",ranking);
  }
  public getMyCompetitionsPaginatedSearch(page:number=0,size:number=15,search:string):Observable<any>{
    let query:string = '';
    if(search!=''){
      query='?page='+page+'&size='+size+'&search='+search;
    }
    else{
      query='?page='+page+'&size='+size;
    }
    return this.http.get<any>(this.api+"/api/v1/rankings/my-competitions"+query);
  }
}
