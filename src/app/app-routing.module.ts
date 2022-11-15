import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckoutComponent } from './checkout/checkout.component';
import { CustomerAccountComponent } from './customer-account/customer-account.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { RegisterSellerComponent } from './register-seller/register-seller.component';
import { RegisterComponent } from './register/register.component';
import { SellerAccountComponent } from './seller-account/seller-account.component';
import { SellerLoginComponent } from './seller-login/seller-login.component';
import { SellerComponent } from './seller/seller.component';
import { StoreComponent } from './store/store.component';
import { StoresComparationComponent } from './stores-comparation/stores-comparation.component';
import { SucessOrderComponent } from './sucess-order/sucess-order.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login-vendedor', component: SellerLoginComponent },
  { path: 'registro', component: RegisterComponent },
  { path: 'registro-vendedor', component: RegisterSellerComponent },
  {
    path: 'store',
    component: MainComponent,
    children: [
      { path: '', component: StoreComponent },
      { path: 'compare', component: StoresComparationComponent },
      { path: 'checkout', component: CheckoutComponent },
      { path: 'success', component: SucessOrderComponent },
      { path: 'account', component: CustomerAccountComponent },
    ],
  },
  {
    path: 'seller',
    component: SellerComponent,
    children: [{ path: '', component: SellerAccountComponent }],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
