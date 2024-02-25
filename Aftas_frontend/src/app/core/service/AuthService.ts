import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { env } from "src/app/environments/environment";
import { TokenStorageService } from "./TokenStorageService";

@Injectable(
    {
        providedIn: 'root'
    }
)
export class AuthService {
    private readonly api:string = env.api;
    constructor(private http:HttpClient,
        private tokenStorageService:TokenStorageService
        ) { }
    login(username: string, password: string): Observable<any>{
        return this.http.post(this.api+'/api/v1/auth/login', {username, password});
    }
    // register(data:UserRegister):Observable<any> {
    //     return this.http.post(this.api+'/api/v1/auth/register', data);
    // }
    getUserInfo():Observable<any>{
        return this.http.get(this.api+'/api/v1/auth/principal');
    }
    refreshToken():Observable<any>{
    return this.http.get(this.api+'/api/v1/auth/refresh',{headers:{'Authorization':`Bearer ${this.tokenStorageService.getRefreshToken()}`}});
    }
}