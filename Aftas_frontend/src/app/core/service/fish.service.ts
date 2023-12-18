import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FishService {

  constructor(private http:HttpClient) { }
  public getcount():Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/fishes/count");
  }
  public getFishes():Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/fishes");
  }
}
