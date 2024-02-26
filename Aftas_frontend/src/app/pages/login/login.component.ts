import { Component } from '@angular/core';
import { UserLogin } from 'src/app/core/model/UserLogin';
import { AuthService } from 'src/app/core/service/AuthService';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';
import { LoginValidation } from 'src/app/core/validators/LoginValidation';
import {  Router } from '@angular/router';
import { UserResponseInfo } from 'src/app/core/model/UserResponseInfo';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user_login:UserLogin = {} as UserLogin;
  error: string = '';
  success: string = '';
  login_loading: boolean = false;
  login_validation:{ [key: string]: string } = {};

  constructor(private authService:AuthService,
    private loginValidation:LoginValidation,
    private  router:Router,
    private tokenStorageService:TokenStorageService
    ) { }
  ngOnInit(): void {
    console.log('LoginComponent');
  }
  login() {
    console.log(this.user_login);
    this.login_validation = this.loginValidation.validate(this.user_login);
    if(Object.keys(this.login_validation).length === 0){
        this.login_loading = true;
        this.authService.login(this.user_login.email, this.user_login.password).subscribe(
          (response) => {
              console.log(response);
              this.tokenStorageService.saveToken(response.accessToken);
              this.tokenStorageService.saveRefreshToken(response.refreshToken);
              this.authService.getUserInfo().subscribe(
                (response) => {
                  console.log(response);
                  console.log("here");
                  this.tokenStorageService.saveUser(response as UserResponseInfo);
                  this.router.navigate(['/']);
                },
                (error) => {
                  this.success = '';
                  this.login_loading = false;
                  this.error = 'Member Not Exists OR Password is incorrect OR is Locked';
                }
              );
          },
          (error) => {
            this.success = '';
            this.login_loading = false;
            this.error = 'Member Not Found OR Password is incorrect';
          }
        );
    }

  }
  

}
