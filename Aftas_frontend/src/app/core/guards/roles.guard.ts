import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../service/TokenStorageService';

@Injectable({
  providedIn: 'root'
})
export class RolesGuard implements CanActivate {
  constructor(private tokenStorageService:TokenStorageService,private router:Router) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const user = this.tokenStorageService.getUser();

    if (user && user.role) {
      const expectedRoles:string[] = route.data['expectedRoles'];

      if (expectedRoles.includes(user.role)) {
        return true;
      } else {
        this.router.navigate(['/no-access']);
        return false;
      }
    } else {
      this.router.navigate(['/no-access']);
      return false;
    }
  }
  
}
