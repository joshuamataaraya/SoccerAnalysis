
[Source](http://www.codestyle.co/Guidelines/angularjs "Permalink to AngularJS Guidelines - Codestyle.co")

# AngularJS Guidelines


####

#  Table of Contents

* [Single Responsibility](#single-responsibility)
* [Immediately Invoked Function Expressions](#immediately-invoked-function-expressions)
* [Modules](#modules)
* [Controllers](#controllers)
* [Services](#services)
* [Factories](#factories)
* [Directives](#directives)
* [Resolving Promises for a Controller](#resolving-promises-for-a-controller)
* [Manual Dependency Injection](#manual-dependency-injection)
* [Minification and Annotation](#minification-and-annotation)
* [Exception Handling](#exception-handling)
* [Application Structure](#application-structure)
* [Angular $ Wrapper Services](#angular-$-wrapper-services)
* [Comments](#comments)


#  Single Responsibility

Defining a single component per file helps with code maintenance. Rather than defining a module (and its dependencies), a controller, and a factory all in the same file, separate each one into their own files.

#  Immediately Invoked Function Expressions

Wrapping your AngularJS components in an Immediately Invoked Function Expression (IIFE). This helps to prevent variables and function declarations from living longer than expected in the global scope, which also helps avoid variable collisions.

This becomes even more important when your code is minified and bundled into a single file for deployment to a production server by providing variable scope for each file.

Example

    /* recommended */
    // logger.js
    (function () {
       angular
       .module('app')
       .factory('logger', logger);

       function logger () { }
    })();

    // storage.js
    (function () {
       angular
       .module('app')
       .factory('storage', storage);

       function storage () { }
    })();

Additional information

Remarks

For brevity only, the rest of the examples in this guide may omit the IIFE syntax.

#  Modules

## Definitions (aka Setters)

When you include only a single component per file, there is rarely a need to introduce a variable for the module. Instead, use the simple getter syntax.

Example

    /* avoid */
    var app = angular.module('app', [
      'ngAnimate',
      'ngRoute',
      'app.shared'
      'app.dashboard'
    ]);

    /* recommended */
    angular
       .module('app', [
         'ngAnimate',
         'ngRoute',
         'app.shared'
         'app.dashboard'
       ]);

## Getters

When using a module, using chaining with the getter syntax produces more readable code and avoids variables collisions or leaks.

Example

    /* avoid */
    var app = angular.module('app');
    app.controller('SomeController' , SomeController);

    function SomeController() { }

    /* recommended */
    angular
       .module('app')
       .controller('SomeController' , SomeController);

    function SomeController() { }

## Setting vs Getting

A module should only be created once. Use `angular.module('app', []);` to set a module and `angular.module('app');` to get a module.

## Named vs Anonymous Functions

Using named functions for callbacks produces more readable code, is much easier to debug, and reduces the amount of nested callback code.

Example

    /* avoid */
    angular
       .module('app')
       .controller('Dashboard', function () { });
       .factory('logger', function () { });

    /* recommended */
    // dashboard.js
    angular
       .module('app')
       .controller('Dashboard', Dashboard);

    function Dashboard () { }

    // logger.js
    angular
       .module('app')
       .factory('logger', logger);

    function logger () { }

#  Controllers

## `controllerAs` syntax in the view

Use the `controllerAs` syntax over the `classic controller with $scope` syntax. This syntax is closer to that of a JavaScript constructor and it promotes the use of binding to a "dotted" object in the View (e.g. `customer.name` instead of `name`), which is more contextual, easier to read, and avoids any reference issues that may occur without "dotting". It can also help avoid using `$parent` calls in Views with nested controllers.

Example

    <!-- avoid -->
    <div ng-controller="Customer">
       {{ name }}
    </div>

    <!-- recommended -->
    <div ng-controller="Customer as customer">
       {{ customer.name }}
    </div>

## `controllerAs` syntax in the controller

Using the `controllerAs` syntax instead of the `classic controller with $scope` syntax helps avoid the temptation of using `$scope` methods inside a controller when it might be better to avoid them or move them to a factory. Consider using `$scope` in a factory, or if in a controller just when needed. For example when publishing and subscribing events using `$emit`, `$broadcast`, or , or consider moving these uses to a factory and invoke form the controller.

`this``controllerAs` syntax uses `this`, which gets bound to `$scope` and is syntactic sugar over `$scope`. You can still bind to the View and still access `$scope` methods.

Example

    /* avoid */
    function Customer ($scope) {
       $scope.name = {};
       $scope.sendMessage = function () { };
    }

    /* recommended - but see next section */
    function Customer () {
       this.name = {};
       this.sendMessage = function () { };
    }

## controllerAs with the view model

The `this` keyword is contextual and, when used within a function inside a controller, may change its context. Capturing the context of `this` avoids the problem. Choose a consistent variable name such as `vm`, which stands for ViewModel.

Example

    /* avoid */
    function Customer () {
       this.name = {};
       this.sendMessage = function () { };
    }

    /* recommended */
    function Customer () {
       /* jshint validthis: true */
       var vm = this;
       vm.name = {};
       vm.sendMessage = function () { };
    }

Note

The `/* jshint validthis: true */` comment avoids any jshint warnings.

## Bindable Members Up Top

Placing bindable memnbers at the top of the controller in alphabetical order makes it easy to ready and helps you instantly identify which members of the controller can be bound and used in the View.

When inline anonymous functions are more than 1 line of code they can reduce the readability. Defining the functions below the bindable members (the functions will be hoisted) moves the implementation details down, keeps the bindable members up top, and makes it easier to read.

Example

    /* avoid */
    function Sessions() {
       var vm = this;

       vm.gotoSession = function() {
          /* ... */
       };
       vm.refresh = function() {
          /* ... */
       };
       vm.search = function() {
          /* ... */
       };
       vm.sessions = [];
       vm.title = 'Sessions';
    }

    /* recommended */
    function Sessions() {
       var vm = this;

       vm.gotoSession = gotoSession;
       vm.refresh = refresh;
       vm.search = search;
       vm.sessions = [];
       vm.title = 'Sessions';

       function gotoSession() {
          /* */
       }

       function refresh() {
          /* */
       }

       function search() {
          /* */
       }
    }

## Defer Controller Logic

Placing logic in a service or a factory, rather than directly in the controller, can lead to better resuse across multiple controllers. It is also easier to isolate in a unit test and allows the calling logic in the controller to be easily mocked. It also helps remove dependencies and hides implementation details from the controller.

Example

    /* avoid */
    function Order ($http, $q) {
       var vm = this;
       vm.checkCredit = checkCredit;
       vm.total = 0;

       function checkCredit () {
          var orderTotal = vm.total;
          return $http.get('api/creditcheck').then(function (data) {
             var remaining = data.remaining;
             return $q.when(!!(remaining &gt; orderTotal));
          });
       };
    }

    /* recommended */
    function Order (creditService) {
       var vm = this;
       vm.checkCredit = checkCredit;
       vm.total = 0;

       function checkCredit () {
          return creditService.check();
       };
    }

## Assigning Controllers

Pairing the controller in the route allows different routes to invoke different pairs of controllers and views. When controllers are assigned in the view using `ng-controller`, that view is always associated with the same controller.

Note

If a View is loaded via another means besides a route, then use the `ng-controller="Avengers as vm"` syntax.

Example

    // route-config.js
    angular
    .module('app')
    .config(config);

    function config ($routeProvider) {
    $routeProvider
      .when('/avengers', {
        templateUrl: 'avengers.html',
        controller: 'Avengers',
        controllerAs: 'vm'
      });
    }

Example

    <!-- avengers.html -->
    <div>
    </div>

Additional information

See also

* [Do you like your Angular controllers with or without sugar? ][4]
* [https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$emit ][5]
* [https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$broadcast ][6]
* [https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$on ][7]
* [https://docs.angularjs.org/api/ng/directive/ngController ][8]

#  Services

All AngularJS services are singletons, which means that there is only one instance of a given service per injector. Services are instantiated with the `new` keyword. Use `this` for public methods and variables. You can also use a factory instead of a service, which can introduce greater consistency.

Example

    // service
    angular
       .module('app')
       .service('logger', logger);

    function logger () {
       this.logError = function (msg) {
          /* */
       };
    }

    // factory
    angular
      .module('app')
      .factory('logger', logger);

    function logger () {
       return {
          logError: function (msg) {
             /* */
          }
       };
    }

#  Factories

Factories are singletons which should have a single responsibility that is encapsulated by its context. Declaring all of the callable members of the service at the top makes it easy to read and helps you instantly identify which members of the service can be called and must be unit tested (and/or mocked).

Example

    function dataService () {
       var someValue = '';
       var service = {
          save: save,
          someValue: someValue,
          validate: validate
       };
       return service;

       ////////////
       function save () {
          /* */
       };

       function validate () {
          /* */
       };
    }

Additional information

See also

* [http://en.wikipedia.org/wiki/Single_responsibility_principle ][9]
* [Revealing Module Pattern ][10]

#  Directives

## One directive per file

Creating one directive per file, with the name the file matching the directive, makes them easier to maintain.

Example

    /**
    * @desc order directive that is specific to the order module at a company named Acme
    * @file calendarRange.directive.js
    * @example <div acme-order-calendar-range=""></div>
    */
    angular
       .module('sales.order')
       .directive('acmeOrderCalendarRange', orderCalendarRange)

    /**
    * @desc spinner directive that can be used anywhere across the sales app at a company named Acme
    * @file customerInfo.directive.js
    * @example <div acme-sales-customer-info=""></div>
    */
    angular
       .module('sales.widgets')
       .directive('acmeSalesCustomerInfo', salesCustomerInfo);

    /**
    * @desc spinner directive that can be used anywhere across apps at a company named Acme
    * @file spinner.directive.js
    * @example <div acme-shared-spinner=""></div>
    */
    angular
       .module('shared.widgets')
       .directive('acmeSharedSpinner', sharedSpinner);


## Restrict to Elements and Attributes

If the directive makes sense as a standalone element, allow restrict `E` (custom element) and optionally restrict `A` (custom attribute). Generally, `E``A``EA` but prefer `E` when its standalone and `A` when it enhances its existing DOM element.

Example

    <!-- recommended -->
    <my-calendar-range></my-calendar-range>
    <div my-calendar-range=""></div>

Example

    /* recommended */
    angular
       .module('app.widgets')
       .directive('myCalendarRange', myCalendarRange);

    function myCalendarRange () {
       var directive = {
          link: link,
          templateUrl: '/template/is/located/here.html',
          restrict: 'EA'
       };
       return directive;

       function link(scope, element, attrs) {
          /* */
       }
    }

Additional information

See also

* [https://docs.angularjs.org/api/ngAnimate ][11]
* [https://docs.angularjs.org/api/ng/directive/ngShow ][12]
* [https://docs.angularjs.org/api/ng/directive/ngHide ][13]

#  Resolving Promises for a Controller

## Controller Activation Promises

Placing start-up logic in a consistent place in the controller makes it easier to locate, more consistent to test, and helps avoid spreading out the activation logic across the controller. Typically this should be done in an `activate` function.

Example

    function Avengers(dataservice) {
       var vm = this;
       vm.avengers = [];
       vm.title = 'Avengers';

       activate();

       ////////////

       function activate() {
          return dataservice.getAvengers().then(function(data) {
             vm.avengers = data;
             return vm.avengers;
          });
       }
    }

## Route Resolve Promises

When a controller depends on a promise to be resolved, resolve those dependencies in the `$routeProvider` before the controller logic is executed. Using a route resolve allows the promise to resolve before the controller logic executes.

Example

    // route-config.js
    angular
       .module('app')
       .config(config);

    function config ($routeProvider) {
       $routeProvider
          .when('/avengers', {
             templateUrl: 'avengers.html',
             controller: 'Avengers',
             controllerAs: 'vm',
             resolve: {
                moviesPrepService: function (movieService) {
                   return movieService.getMovies();
                }
             }
          });
    }

    // avengers.js
    angular
       .module('app')
       .controller('Avengers', Avengers);

    function Avengers (moviesPrepService) {
       var vm = this;
       vm.movies = moviesPrepService.movies;
    }

Note

If you need to conditionally cancel the route before you start using the controller or before it's activated, use a route resolver instead.

Additional information

See also

* [https://docs.angularjs.org/api/ngRoute/provider/$routeProvider ][14]

#  Manual Dependency Injection

To prevent parameters from being converted to mangled variables, avoid using the shortcut syntax of declaring dependencies without using a minification-safe approach.

Using `$inject` to manually identify your dependencies mirros the technique used by `ng-annotate` and safeguards your dependencies from being vulernable to minification issues when parameters may be mangled.

Example

    angular
       .module('app')
       .controller('Dashboard', Dashboard);

    Dashboard.$inject = ['$location', '$routeParams', 'common', 'dataservice'];

    function Dashboard($location, $routeParams, common, dataservice) {
    }

Additional information

See also

* [https://github.com/olov/ng-annotate ][15]

#  Minification and Annotation

Using `ng-annotate` for Gulp or Grunt by commenting functions that need automated dependency injection with `/** @ngInject */` will safeguard your code from any dependencies that may not be using minification-safe practices.

Note

Starting from AngularJS 1.3 use the `ngStrictDi` parameter on the `ng-app` directive: `` to create the injector in "strict-di" mode. This causes the application to fail to invoke functions which do not use explicit function annotation (these may not be minification safe). Debugging info will be logged to the console to help track down the offending code.

Additional information

See also

* [https://github.com/olov/ng-annotate ][15]
* [http://gulpjs.com ][16]
* [http://gruntjs.com ][17]
* [https://docs.angularjs.org/api/ng/directive/ngApp ][18]
* [https://www.npmjs.org/package/gulp-ng-annotate ][19]
* [https://www.npmjs.org/package/grunt-ng-annotate ][20]

#  Exception Handling

To provide a consistent manner for customizing how exeptions are handled, use a [decorator][21] at configuration time using the service to perform custom actions when exceptions occur. service on the [`$exceptionHandler`][22] service to perform custom actions when exceptions occur.

Example

    angular
       .module('app.exception')
       .config(['$provide', exceptionConfig]);

    function exceptionConfig($provide) {
       $provide.decorator('$exceptionHandler', ['$delegate', '$log', extendExceptionHandler]);
    }

    function extendExceptionHandler($delegate, $log) {
       return function (exception, cause) {
          $delegate(exception, cause);
          var errorData = {
             exception: exception,
             cause: cause
          };
          var msg = 'ERROR PREFIX' + exception.message;
          $log.error(msg, errorData);

          // Log during dev with http://toastrjs.com
          // or any other technique you prefer
          toastr.error(msg);
       };
    }

#  Application Structure

## Follow the LIFT Principle

Providing a consistent structure that scales well, is modular, and makes it easy to find code quickly increases developer efficiency and productivity. You can achieve this by following the LIFT principle:

1. `L`ocate your code quickly
2. `I`dentify the code at a glance
3. Keep the `F`lattest structure you can
4. `T`ry to follow the (DRY) pattern (DRY) pattern

#  Angular $ Wrapper Services

Use Use and [`$window`][23] instead of `document` and `window`. They are more easily testable than using `document` and `window`.

Use [`$timeout`][24] and [`$interval`][25] instead of `setTimeout` and `setInterval`. They are more easily testable and handle AngularJS's digest cycle, thereby keeping data binding in sync.

#  Comments

Using [`jsDoc`][26] syntax to document function names, description, params and returns allows you to generate documentation from your code rather than writing it from scratch and provides consistency by using a common tool.

Example

    angular
       .module('app')
       .factory('logger', logger);

    /**
    * @name logger
    * @desc Application wide logger
    */
    function logger ($log) {
       var service = {
       logError: logError
       };
       return service;

       ////////////

       /**
       * @name logError
       * @desc Logs errors
       * @param {String} msg Message to log
       * @returns {String}
       */
       function logError(msg) {
          var loggedMsg = 'Error: ' + msg;
          $log.error(loggedMsg);
          return loggedMsg;
       };
    }


* [About][28]


[1]: http://www.codestyle.co/img/codestyle-full.png
[2]: /#guidelines
[3]: https://github.com/johnpapa/angularjs-styleguide
[4]: http://www.johnpapa.net/do-you-like-your-angular-controllers-with-or-without-sugar/
[5]: https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$emit
[6]: https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$broadcast
[7]: https://docs.angularjs.org/api/ng/type/$rootScope.Scope#$on
[8]: https://docs.angularjs.org/api/ng/directive/ngController
[9]: http://en.wikipedia.org/wiki/Single_responsibility_principle
[10]: http://addyosmani.com/resources/essentialjsdesignpatterns/book/#revealingmodulepatternjavascript
[11]: https://docs.angularjs.org/api/ngAnimate
[12]: https://docs.angularjs.org/api/ng/directive/ngShow
[13]: https://docs.angularjs.org/api/ng/directive/ngHide
[14]: https://docs.angularjs.org/api/ngRoute/provider/$routeProvider
[15]: https://github.com/olov/ng-annotate
[16]: http://gulpjs.com
[17]: http://gruntjs.com
[18]: https://docs.angularjs.org/api/ng/directive/ngApp
[19]: https://www.npmjs.org/package/gulp-ng-annotate
[20]: https://www.npmjs.org/package/grunt-ng-annotate
[21]: https://docs.angularjs.org/api/auto/service/$provide#decorator
[22]: https://docs.angularjs.org/api/ng/service/$exceptionHandler
[23]: https://docs.angularjs.org/api/ng/service/$window
[24]: https://docs.angularjs.org/api/ng/service/$timeout
[25]: https://docs.angularjs.org/api/ng/service/$interval
[26]: http://usejsdoc.org/
[27]: http://www.jshint.com/docs/
[28]: /Home/About
  