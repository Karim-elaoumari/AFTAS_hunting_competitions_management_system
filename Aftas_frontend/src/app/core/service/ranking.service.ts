import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RankingService {

  constructor(private http:HttpClient) {
    

   }
   public getRankingsOfCompetition(id:String):Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/rankings/competition/"+id);
   }
}
