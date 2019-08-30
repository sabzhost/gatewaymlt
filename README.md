<p align="center">
  <img src="https://help.zarinpal.com/logo2.png" height="80"/>
</p>

### ZarinPal Cordova Plugin
[![npm version](https://img.shields.io/npm/v/cordova-plugin-zarinpal.svg)](https://www.npmjs.com/package/cordova-plugin-zarinpal) [![npm downloads](https://img.shields.io/npm/dm/cordova-plugin-zarinpal.svg)](https://www.npmjs.com/package/cordova-plugin-zarinpal)

---

[ZarinPal](https://onesignal.com/) is the first pioneer payer in Iran. This plugin makes it easy to integrate your [Cordova](http://cordova.apache.org/) based (e.g. [Ionic](http://ionicframework.com/), [PhoneGap](https://phonegap.com/), PhoneGap Build, [Intel XDK](https://software.intel.com/en-us/intel-xdk/documentation) or [Sencha Touch](https://www.sencha.com/products/touch/)) app with ZarinPal. 

#### Installation and Setup
##### 1. Initialize
In `deviceready` event use `window.ZarinPal.initialize(MerchantId, IsSandbox, Result)` code. Sample:
```javascript
window.ZarinPal.initialize(
  "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
  true,
  function(result) {
    console.log("Initialize success");
  }
);
```
##### 2. Start Payment
To start payment use `window.ZarinPal.startPayment(AutoShowPaymentPage, Amount, Description, Email, Phone, Result)` code.
Sample auto show payment page:
```javascript
window.ZarinPal.startPayment(
  true,
  2000,
  'Test Payment',
  'rezagah.milad@gmail.com',
  '',
  function(result) {
    //Success (true/false): result.Success
    //RefId: result.RefID
    console.log(result);
  }
)
```
Sample manual show payment page:
```javascript
window.ZarinPal.startPayment(
  false,
  2000,
  'Test Payment',
  'rezagah.milad@gmail.com',
  '',
  function(result) {
    //Status: result.Status
    //Authority: result.Authority
    if (result.Status === 100) {
      window.ZarinPal.showPayment(
        null,
        function(error) {
          console.log(error);
        }
      );
    }
  }
)
```
##### 3. Verification Payment
Add `window.ZarinPal.verificationPayment(Success, Error)` code in `resume` event. to fire `resume` event add `document.addEventListener("resume", Function, false)` in your `deviceready` event. Sample:
```javascript
document.addEventListener("deviceready", function () {
  document.addEventListener("resume", onResume, false);
}, false);
      
function onResume() {
  console.log('onResume');
}
```
##### Manual Show Payment Page
To manually show payment page use `window.ZarinPal.showPayment(Success, Error)` code.

#### Sample
You can see cordova sample from [index.html](https://raw.githubusercontent.com/VinoosIr/cordova-plugin-zarinpal/master/sample/index.html).

#### Construct 2 Plugin
You can download Construct 2 plugin from [Vinoos.ir](http://vinoos.ir/help/plugins/zarinpal.php).

#### Demo Project
To make things easier, we have published demo projects for [Cordova WebView](https://github.com/VinoosIr/cordova-plugin-zarinpal/blob/master/demo/ZarinPal.apk?raw=true) and [Cocoon Canvas+](https://github.com/VinoosIr/cordova-plugin-zarinpal/blob/master/demo/ZarinPal-Canvas.apk?raw=true)