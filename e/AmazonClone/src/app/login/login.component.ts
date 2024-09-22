import { Component } from '@angular/core';
import { AppComponent } from '../app.component';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(public http: HttpClient, public app: AppComponent) { }
  user: any={};
  isLogin: boolean = true; // Default to login form
  username: string = '';
  password: string = '';

  login() {
    let url = this.app.baseUrl + 'login/log';
    let obj = [this.username, this.password];
    this.http.post(url, obj).subscribe((data: any) => {
      if (data == null) {
        window.alert('Something is wrong')
      }
      else {
        if (data.id < 1) {
          window.alert(data.errorMessage)
        }
        else {
          this.app.id = data.id;
          this.app.whatToShow = data.accountType;
        }
      }
    })
  }
  signup() {
    // console.log(this.user)
    if (this.user.name == null || this.user.userName == null || this.user.password == null || this.user.accountType == null)
      window.alert("All fields are mandetory");
    else {
      this.isLogin = false;
      let url = this.app.baseUrl + 'signup/register';
      this.http.post<any>(url, this.user)
        .subscribe(response => {
          // console.log(response);
          window.alert('Account created successfully!');
          // // Reset user object or clear form fields
          this.user = {};
          // // Optionally, switch back to the login form
          this.isLogin = true;
        });
    }
  }
  showSignupForm() {
    this.isLogin = false; // Switch back to login form
  }
  showLoginForm() {
    this.isLogin = true; // Switch back to login form
  }
}