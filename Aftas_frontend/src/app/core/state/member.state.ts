export class MemberState { 
    public static members: any[] = [];
    public static count: number = -1;
    public static getMembers(): any[] {
        return MemberState.members;
    }
    public static setMembers(members: any[]) {
        MemberState.members = members;
    }
    public static getCount(): number {
        return MemberState.count;
    }
    public static setCount(count: number) {
        MemberState.count = count;
    }
    public static addMember(member: any) {
        MemberState.members.push(member);
    }
}
