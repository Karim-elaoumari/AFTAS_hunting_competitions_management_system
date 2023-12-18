import { RankingComp } from "./RankingComp";

export interface CompetitionResp {
        code: string,
        location: string,
        date: string,
        start_time: string,
        end_time: string,
        amount: number,
        max_participants: number,
        description: string,
        rankings :RankingComp[]
}