import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  whatToShow: number = 0;
  changeWhatToShow(num: number) {
    this.whatToShow = num;
  }
  name: string = '';
  constructor(public http: HttpClient, public app: AppComponent) {
    let url = app.baseUrl + 'login/getName' + app.id;
    http.get(url).subscribe((data: any) => {
      if (data == null) {
        window.alert('something is wrong');
      }
      else {
        this.name = data[0];
      }
    });
  }
}