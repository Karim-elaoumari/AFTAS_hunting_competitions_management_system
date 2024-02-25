import { Injectable } from "@angular/core";
import { UserLogin } from "../model/UserLogin";

@Injectable(
    {
        providedIn: 'root'
    }
)
export class LoginValidation{
    validate(userLogin:UserLogin):{ [key: string]: string } {
        const errors: { [key: string]: string } = {};
        if (userLogin.email == null || userLogin.email.length === 0) {
            errors["email"] = "Email is required";
          }
        if (userLogin.password == null || userLogin.password.length === 0) {
        errors["password"] = "Password is required";
        } 
        return errors;

    }
    // private isValidEmail(email: string): boolean {
    //     const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    //     return emailRegex.test(email);
    //   }
    
    // private isValidPassword(password: string): boolean {
    // const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    // return passwordRegex.test(password);
    // }
}