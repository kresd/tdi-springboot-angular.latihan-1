import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductsListComponent } from './products-list/products-list.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { SearchProductsComponent } from './search-products/search-products.component';



const routes: Routes = [
    { path: '', redirectTo: 'product', pathMatch: 'full' },
    { path: 'add', component: CreateProductComponent },
    { path: 'product', component: ProductsListComponent },
    
    { path: 'findbyage', component: SearchProductsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
