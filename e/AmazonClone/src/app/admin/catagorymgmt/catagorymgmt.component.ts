import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-catagorymgmt',
  templateUrl: './catagorymgmt.component.html',
  styleUrls: ['./catagorymgmt.component.css']
})
export class CatagorymgmtComponent {
  list: any;
  constructor(public http: HttpClient, public app: AppComponent) {
      }
  ngOnInit(): void {
    this.loadCategories();  // Load categories when the component initializes
  }

  loadCategories(): void {
    const url = this.app.baseUrl + 'admin/getAllCategories';
    this.http.get<any>(url).subscribe(data => {
      if (data == null) {
        window.alert('Something is Wrong');
      } else {
        this.list = data;
      }
    });
  }

  name: string = '';
  addCategory() {
    if (!this.name.trim()) {
      window.alert('Please enter a category');
      return;
    }
    let url = this.app.baseUrl + 'admin/addNewCategory' + this.app.id;
    this.http.post(url, this.name).subscribe((data: any) => {
      if (data == null) {
        window.alert('something is wrong');
      }
      else {
        this.list.push(data);
        this.name = data[0];
      }
    });
  }
  deleteCategory(id: number): void {
    if (window.confirm('Are you sure you want to delete this category?')) {
      const url = `${this.app.baseUrl}admin/deleteCategory/${id}`;
      this.http.delete(url).subscribe(response => {
        this.loadCategories();
        return response;
      });
    }
  }
}