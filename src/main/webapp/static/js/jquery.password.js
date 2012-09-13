/*
 * password 1.0 - Random password generator for jQuery
 *
 * Copyright (c) 2010 KRM Associates, Inc.
 *
 * Licensed under the GPL license:
 *   http://www.gnu.org/licenses/gpl.html
 *
 */
(function($){
	$.extend({
	  password: function (length, special, number) {
		var password;
		var randomNumber;
                var specFlag = false;
                var numFlag = false;
		if(length == undefined){
			var length = 8;
		}
		if(special == undefined){
			var special = true;
		}
		if(number == undefined){
			var number = true;
		}
                while ((!specFlag) || (!numFlag)) {
                    password = "";
                    specFlag = false;
                    numFlag = false;
                    while(password.length < length){
                            randomNumber = (Math.floor((Math.random() * 100)) % 90) + 33;

                            if ((randomNumber >=33) && (randomNumber <=38) && (randomNumber!=34)) {
                                // !#$%&
                                if (special) {
                                    specFlag = true;
                                    password += String.fromCharCode(randomNumber);
                                }
                            }else if (randomNumber ==34){
                            }else if ((randomNumber >=39) && (randomNumber <=47)) {
                            }else if ((randomNumber >=58) && (randomNumber <=63)) {
                            }else if ((randomNumber >=91) && (randomNumber <=96)) {
                                //nop
                            } else if ((randomNumber >=48) && (randomNumber <=57)) {
                                if (number) {
                                    numFlag = true;
                                    password += String.fromCharCode(randomNumber);
                                }
                            } else {
                                password += String.fromCharCode(randomNumber);
                            }

                    }
                    
                    if (!special) specFlag = true;
                    if (!number) numFlag = true;
                }

		return password;
	  }
	});
})(jQuery);