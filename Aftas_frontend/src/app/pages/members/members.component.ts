import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Member } from 'src/app/core/model/Member';
import { MemberService } from 'src/app/core/service/member.service';

@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.css']
})
export class MembersComponent {
    count_members = -1;
    loading = true;
    page= 0;
    size= 5;
    search='';
    members: Member[] = [];
  
    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private memberService:MemberService,
    ) { }
  
    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        this.page = +params['page'] || 0;
        this.size = +params['size'] || 5;
      });
      this.getMembers(this.page);
      this.getmembercount();
    }
    getMembers(page:number=0){
      this.page=page;
      this.memberService.getMembersPaginatedSearch(page,this.size,'').subscribe(
        (data)=>{
          this.members=data.data;
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
      this.setQueryInUrl();
    }
    searchMembers(search:string=''){
      this.page=0;
      this.memberService.getMembersPaginatedSearch(this.page,this.size,search).subscribe(
        (data)=>{
          this.members=data.data;
          this.checkLoadingComplete();
        },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
      this.setQueryInUrl();
    }
    getmembercount(){
      this.memberService.getcount().subscribe(
        (data)=>{
        this.count_members=data.data;
        this.checkLoadingComplete();
      },
        (error:any)=>{
          console.log(error);
          this.loading = false;
        }
      );
    }
    checkLoadingComplete() {
      if (this.count_members !== -1 && this.members.length !== 0) {
        this.loading = false;
      }
    }
    setQueryInUrl() {
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: { page: this.page, size: this.size, search: this.search },
        queryParamsHandling: 'merge',
      });
    }

}
