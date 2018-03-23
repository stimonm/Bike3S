import {Component} from "@angular/core";
import * as $ from "jquery";

@Component({
    selector: 'simulate-component',
    template: require('./simulate.component.html'),
    styles: [require('./simulate.component.css')]
})
export class SimulateComponent {

    ngOnInit() {
        $('body').css({
            "background": "#F0F0F0"
        });
    }

}