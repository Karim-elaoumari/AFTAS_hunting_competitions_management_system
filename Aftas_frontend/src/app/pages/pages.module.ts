import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SharedModule } from '../shared/shared.module';
import { CompetitionsComponent } from './competitions/competitions.component';
import { CompetitionDetailComponent } from './competition-detail/competition-detail.component';
import { AddCompetitionComponent } from './add-competition/add-competition.component';
import { MembersComponent } from './members/members.component';
import { AddMemberComponent } from './add-member/add-member.component';
import { FishesComponent } from './fishes/fishes.component';
import { AddFishComponent } from './add-fish/add-fish.component';
import { LevelsComponent } from './levels/levels.component';
import { AddLevelComponent } from './add-level/add-level.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { NoAccessComponent } from './no-access/no-access.component';




@NgModule({
  declarations: [
    DashboardComponent,
    CompetitionsComponent,
    CompetitionDetailComponent,
    AddCompetitionComponent,
    MembersComponent,
    AddMemberComponent,
    FishesComponent,
    AddFishComponent,
    LevelsComponent,
    AddLevelComponent,
    LoginComponent,
    NoAccessComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,

  ]
})
export class PagesModule { }
