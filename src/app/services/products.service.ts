import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/models/Category';
import { Product } from 'src/models/Product';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  constructor(private http: HttpClient) {}

  public getProducts(categoryId?: string): Observable<Array<Product>> {
    let params = new HttpParams();
    if (categoryId && categoryId !== 'all') params = params.set('category', categoryId);

    return this.http.get<Array<Product>>(`http://localhost:8080/products`, {
      params,
    });
  }

  public getCategories(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>('http://localhost:8080/categories');
  }
}
