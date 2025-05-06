import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AdminEditPropertyComponent } from './components/admin-edit-property/admin-edit-property.component';
import { AdminViewPropertyComponent } from './components/admin-view-property/admin-view-property.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { MyInquiryComponent } from './components/my-inquiry/my-inquiry.component';
import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';
import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';
import { UserAddInquiryComponent } from './components/user-add-inquiry/user-add-inquiry.component';
import { AdminAddPropertyComponent } from './components/admin-add-property/admin-add-property.component';
import { UserViewPropertiesComponent } from './components/user-view-properties/user-view-properties.component';
import { AdminControlPanelComponent } from './components/admin-control-panel/admin-control-panel.component';
import { EditInquiryComponent } from './components/edit-inquiry/edit-inquiry.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';
import { UserViewInquiryComponent } from './components/user-view-inquiry/user-view-inquiry.component';
import { UserGuard } from './components/user.guard';
import { AdminGuard } from './components/admin.guard';
import { BothGuard } from './components/both.guard';



const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:SignupComponent},
  {path:'adminnav',component:AdminnavComponent},
  {path:'usernav',component:UsernavComponent},
  {path:'myinquiries',component:MyInquiryComponent,canActivate:[UserGuard]},
  {path:'inquiries',component:AdminViewInquiryComponent,canActivate:[AdminGuard]},
  {path:'viewfeedback',component:AdminViewFeedbackComponent,canActivate:[BothGuard]},
  {path:'addfeedback',component:UserAddFeedbackComponent,canActivate:[UserGuard]},
  {path:"admin-view-property",component:AdminViewPropertyComponent,canActivate:[AdminGuard]},
  { path: 'admin-edit-property/:id', component: AdminEditPropertyComponent,canActivate:[AdminGuard] },
  {path:'user-view-property',component:UserViewPropertiesComponent,canActivate:[UserGuard]},
  {path:'user-add-inquiry/:id',component:UserAddInquiryComponent,canActivate:[UserGuard]},
  {path:'controlpanel',component:AdminControlPanelComponent,canActivate:[AdminGuard]},
  {path:'editInquiry/:id',component:EditInquiryComponent,canActivate:[AdminGuard]},
  {path:'addfeedback',component:UserAddFeedbackComponent,canActivate:[UserGuard]},
  {path:'user-view-feedback',component:UserViewFeedbackComponent,canActivate:[UserGuard]},
  {path: 'admin-add-property', component: AdminAddPropertyComponent,canActivate:[AdminGuard]},
  {path:'user-view-inquiry',component:UserViewInquiryComponent,canActivate:[UserGuard]},
  {path:'user-view-property',component:UserViewPropertiesComponent,canActivate:[UserGuard]},
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }