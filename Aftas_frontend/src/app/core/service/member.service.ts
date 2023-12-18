import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http:HttpClient) { }
  public getcount():Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/members/count");
  }
  public getMembers():Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/members");
  }
  public getMember(number:number):Observable<any>{
    return this.http.get<any>("http://localhost:8081/api/v1/members/"+
    number);
  }
  public getMembersPaginatedSearch(page:any,size:any,search:string):Observable<any>{
    let query:string = '';
    if(search!=''){
      query='?page='+page+'&size='+size+'&search='+search;
    }
    else if(page==null && size==null){
      query='';
    }
    else{
      query='?page='+page+'&size='+size;
    }
    return this.http.get<any>("http://localhost:8081/api/v1/members"+query);
  }
  public addMember(member:any):Observable<any>{
    return this.http.post<any>("http://localhost:8081/api/v1/members",member);
  }

}
