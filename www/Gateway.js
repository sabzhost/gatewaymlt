module.exports = {
    initialize: function() {
        cordova.exec(
			null,
			null,
            'GatewayPlugin',
            'initialize',
            []
        ); 
    },
    openUrl: function(url) {
        cordova.exec(
			null,
			null,
            'GatewayPlugin',
            'openUrl',
            [url]
        ); 
    },
    getResult: function() {
        cordova.exec(
			function (result) {
				console.log(result);
			},
			null,
            'GatewayPlugin',
            'getResult',
            []
        ); 
    }
};