import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from '../product.service';
import { Product } from '../product';


@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  products: Observable<Product[]>;


  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.reloadData();

  }

  deleteProducts() {
    this.productService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  deleteProduct(product : Product) {
    this.productService.deleteProduct(product.id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  reloadData() {
    this.products = this.productService.getProductsList();
  }
}
