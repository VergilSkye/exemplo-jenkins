import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ExemploSharedModule } from 'app/shared/shared.module';
import { ExemploCoreModule } from 'app/core/core.module';
import { ExemploAppRoutingModule } from './app-routing.module';
import { ExemploHomeModule } from './home/home.module';
import { ExemploEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ExemploSharedModule,
    ExemploCoreModule,
    ExemploHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ExemploEntityModule,
    ExemploAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class ExemploAppModule {}
