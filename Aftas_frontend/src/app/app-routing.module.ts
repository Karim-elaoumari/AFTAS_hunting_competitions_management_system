import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CompetitionsComponent } from './pages/competitions/competitions.component';
import { AddCompetitionComponent } from './pages/add-competition/add-competition.component';
import { MembersComponent } from './pages/members/members.component';
import { CompetitionDetailComponent } from './pages/competition-detail/competition-detail.component';
import { AddMemberComponent } from './pages/add-member/add-member.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'competitions', component: CompetitionsComponent},
  { path: 'add-competition', component: AddCompetitionComponent},
  { path: 'members', component: MembersComponent },
  { path: 'competitions/:code', component: CompetitionDetailComponent},
  { path:  'add-member', component: AddMemberComponent},
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
