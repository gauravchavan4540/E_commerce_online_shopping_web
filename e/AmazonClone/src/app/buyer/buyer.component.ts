import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-buyer',
  templateUrl: './buyer.component.html',
  styleUrls: ['./buyer.component.css']
})
export class BuyerComponent {
  name: string = '';
  catid: number = 0;
  constructor(public http: HttpClient, public app: AppComponent) {
    let url = app.baseUrl + 'login/getName' + app.id;
    http.get(url).subscribe((data: any) => {
      if (data == null) {
        window.alert('something is wrong');
      }
      else {
        this.name = data[0];
      }
    })
  }
}