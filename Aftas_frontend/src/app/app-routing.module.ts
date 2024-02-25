import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CompetitionsComponent } from './pages/competitions/competitions.component';
import { AddCompetitionComponent } from './pages/add-competition/add-competition.component';
import { MembersComponent } from './pages/members/members.component';
import { CompetitionDetailComponent } from './pages/competition-detail/competition-detail.component';
import { AddMemberComponent } from './pages/add-member/add-member.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './core/guards/auth.guard';
import { RolesGuard } from './core/guards/roles.guard';
import { NoAccessComponent } from './pages/no-access/no-access.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER','ADHERENT']}},
  { path: 'competitions', component: CompetitionsComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER','ADHERENT']}},
  { path: 'add-competition', component: AddCompetitionComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER']}},
  { path: 'members', component: MembersComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER']} },
  { path: 'competitions/:code', component: CompetitionDetailComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER','ADHERENT']}},
  { path:  'add-member', component: AddMemberComponent,canActivate: [AuthGuard,RolesGuard],data: {expectedRoles: ['JURY','MANAGER']}},
  { path: 'login',component: LoginComponent},
  { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  { path: 'no-access',component: NoAccessComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
