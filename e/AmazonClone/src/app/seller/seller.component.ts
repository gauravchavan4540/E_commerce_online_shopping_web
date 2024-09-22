import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent {

  whatToShow: number = 0;
  products: any;
  categories: any;
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

  changeWhatToShow(num: number) {
    this.whatToShow = num;
    this.loadProduct();
    // this.loadCategories();
    return;
  }

  loadProduct() {
    const url = this.app.baseUrl + 'seller/getAllProducts' + this.app.id;
    this.http.get(url).subscribe(data => {
      // console.log(data)
      if (data == null) {
        window.alert('Something is Wrong');
      } else {
        this.products = data;
      }
    });
  }
  
}