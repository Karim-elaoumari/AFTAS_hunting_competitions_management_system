import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserResponseInfo } from 'src/app/core/model/UserResponseInfo';
import { TokenStorageService } from 'src/app/core/service/TokenStorageService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(
    private tokenStorageService:TokenStorageService,
    private router:Router
  ){

  }
  user:UserResponseInfo = {} as UserResponseInfo;
  ngOnInit(): void {
    this.getUserInfo();
  }
  getUserInfo(){
    this.user = this.tokenStorageService.getUser();
  }
  logout(){
    this.tokenStorageService.signOut();
    this.router.navigate(['/login']);
  }

}
