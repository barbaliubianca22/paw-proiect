import { Component } from '@angular/core';

@Component({
  selector: 'app-circle-svg',
  template: `
        <svg xmlns="http://www.w3.org/2000/svg" width="33" height="31" viewBox="0 0 33 31" fill="none">
        <g filter="url(#filter0_i_2322_322)">
            <ellipse cx="16.5" cy="15.5" rx="16.5" ry="15.5" fill="#B0B8DB"/>
        </g>
        <defs>
            <filter id="filter0_i_2322_322" x="0" y="0" width="33" height="35" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
            <feFlood flood-opacity="0" result="BackgroundImageFix"/>
            <feBlend mode="normal" in="SourceGraphic" in2="BackgroundImageFix" result="shape"/>
            <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"/>
            <feOffset dy="4"/>
            <feGaussianBlur stdDeviation="2"/>
            <feComposite in2="hardAlpha" operator="arithmetic" k2="-1" k3="1"/>
            <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"/>
            <feBlend mode="normal" in2="shape" result="effect1_innerShadow_2322_322"/>
            </filter>
        </defs>
        </svg>
  `,
  styles: [] // Stiluri specifice componentei
})
export class CircleSvgComponent {}
