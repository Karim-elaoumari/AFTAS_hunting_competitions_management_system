import { Time } from "@angular/common";

export interface CompetitionReq {
    location: string,
    date: Date,
    start_time: Date,
    end_time: Date,
    amount: number,
    max_participants: number,
    description: string
}