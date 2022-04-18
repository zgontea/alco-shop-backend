import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }
  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>('http://localhost:8080/api/products/all');
  }
}
