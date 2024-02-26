import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { env } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private readonly api:string = env.api;

  constructor(private http:HttpClient) { }
  public getcount():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/members/count");
  }
  public getMembers():Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/members");
  }
  public getMember(number:number):Observable<any>{
    return this.http.get<any>(this.api+"/api/v1/members/"+
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
    return this.http.get<any>(this.api+"/api/v1/members"+query);
  }
  public addMember(member:any):Observable<any>{
    return this.http.post<any>(this.api+"/api/v1/members/adherent",member);
  }
  public addJury(member:any):Observable<any>{
    return this.http.post<any>(this.api+"/api/v1/members/jury",member);
  }
  public addManager(member:any):Observable<any>{
    return this.http.post<any>(this.api+"/api/v1/members/manager",member);
  }
  public activateMember(number:string):Observable<any>{
    return this.http.put<any>(this.api+"/api/v1/members/activate/"+number,{});
  }

}
