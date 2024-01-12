import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { env } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private readonly api:string = env.api;
  constructor(private http:HttpClient) {
    

   }
   public getRankingsOfCompetition(id:String):Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/rankings/competition/"+id);
   }
}
