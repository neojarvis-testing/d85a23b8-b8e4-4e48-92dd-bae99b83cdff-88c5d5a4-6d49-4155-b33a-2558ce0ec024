import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

import { AdminAddPropertyComponent } from './components/admin-add-property/admin-add-property.component';
import { AdminEditPropertyComponent } from './components/admin-edit-property/admin-edit-property.component';
import { AdminViewPropertyComponent } from './components/admin-view-property/admin-view-property.component';
import { UserViewPropertiesComponent } from './components/user-view-properties/user-view-properties.component';

import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { MyInquiryComponent } from './components/my-inquiry/my-inquiry.component';

import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';

import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';


const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:SignupComponent},
  {path:'adminnav',component:AdminnavComponent},
  {path:'usernav',component:UsernavComponent},
  {path:'myinquiries',component:MyInquiryComponent},
  {path:'admin-add-property',component:AdminAddPropertyComponent},
  {path:'inquiries',component:AdminViewInquiryComponent},
  {path:'viewfeedback',component:AdminViewFeedbackComponent},
  {path:'viewproperties',component:UserViewPropertiesComponent},
  {path:'addfeedback',component:UserAddFeedbackComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }