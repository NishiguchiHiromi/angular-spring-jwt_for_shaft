# MySourceAngular

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.1.2.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

2018/12/15
## デプロイ時のpackage.json例
{
  "scripts": {
    "ng": "ng",
    "start": "node server",
    "build": "ng build --prod",
    "postbuild": "npm run deploy",
    "predeploy": "rimraf C:/develop/sts-bundle/workspace/mps-server/src/main/resources/static && mkdirp C:/develop/sts-bundle/workspace/mps-server/src/main/resources/static",
    "deploy": "copyfiles -f dist/** C:/develop/sts-bundle/workspace/mps-server/src/main/resources/static",
    "test": "ng test",
    "lint": "ng lint",
    "e2e": "ng e2e",
    "postinstall": "ng build –aot -prod"
}

## heroku対応
https://qiita.com/DotaKobayashi/items/0d9712c7ab31a29ebb5c
  # package.json
  `npm install express --save`（済）
  "scripts": {
      "start": "node server.js",
      "postinstall": "ng build --aot --target=production"
  },

## ErrorHandler
https://gist.github.com/kazuma1989/1e1f8182af19b7bd571ed593d6b65629
