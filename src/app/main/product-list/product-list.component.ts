import { Component, OnInit } from '@angular/core';
import { Product, ProductService } from '../../../core/openapi';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAllProducts().subscribe(
      response => {
        this.products = response;
      }
    )
  }

}
