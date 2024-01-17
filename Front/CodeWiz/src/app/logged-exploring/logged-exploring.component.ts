import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logged-exploring',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './logged-exploring.component.html',
  styleUrl: './logged-exploring.component.scss'
})
export class LoggedExploringComponent {
  scoreTexts: { [key: string]: boolean } = {
    'showScoreText1': false,
    'showScoreText2': false,
    'showScoreText3': false,
    'showScoreText4': false
  };

  scores: { [key: string]: number } = {
    'score1': 0,
    'score2': 0,
    'score3': 0,
    'score4': 0
  };
  showRankingBox: boolean = false;
  showScore(dreptunghiId: string) {
    this.scoreTexts[dreptunghiId] = !this.scoreTexts[dreptunghiId];

    if (!this.scoreTexts[dreptunghiId]) {
      this.scores[dreptunghiId] = 0;
    }
  }

  showRanking() {
    this.showRankingBox = !this.showRankingBox;
  }
}