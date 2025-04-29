import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';

const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:SignupComponent},
  {path:'adminnav',component:AdminnavComponent},
  {path:'usernav',component:UsernavComponent},
  {path:'user-view-feedback',component:UserViewFeedbackComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
