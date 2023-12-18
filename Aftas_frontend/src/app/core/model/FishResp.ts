import { LevelResp } from "./LevelResp";

export interface FishResp {
       id: number,
       name: string,
       info: string,
       average_weight: number,
       level: LevelResp,
}