/**
 * jquery.strengthindicator.js
 * Copyright (c) 2010 KRM Associates, Inc (www.krminc.com)

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * View the GNU General Public License <http://www.gnu.org/licenses/>.

 * @author Chris McCall (chris.mccall@krminc.com)
 * @date 4/8/2010
 * @projectDescription Strength Indicator Plugin - created with regards to http://plugins.jquery.com/project/pstrength
 * @version 1.0.0
 *
 * @requires jquery.js (tested with 1.3.1)
 * @param indicatortarget: ID of element that will be styled upon password threshold change
 * @param texttarget: ID of element that will have it's text element changed upon password threshold change
 * @param csstarget: the style attribute that you want to change of the indicator target (typically background-color)
 * @param blank: an associative descriptor array of form {style:'', text:''} for when no text is entered in password box
 * @param strengths: _NUMERICALLY INCREASING_ array of point values in the 0-100 range and their associated descriptor arrays of parameters {style:'', text:''}.
 *
*/

(function($) {
    $.fn.strengthindicator = function(options) {
        var defaults = {
            indicatortarget:    "passwordStrength",
            texttarget:         "passwordStrength",
            csstarget:          "background-color",
            blank:              {style:'', text:''},
            strengths:    [
                                [0, {style:'red', text:'Unsecure'}],
                                [30, {style:'orange', text:'Weak'}],
                                [60, {style:'#808080', text:'Moderate'}],
                                [80, {style:'#336633', text:'Strong'}],
                                [100, {style:'green', text:'Secure'}]
                            ]
        };
        var opts = $.extend(defaults, options);

        return this.each(function() {
            var obj = $(this);

            obj.keyup(function(){
               var result = $.fn.indicateStrength($(this).val(),opts);

               if (result) {
                   $('#' + opts.indicatortarget).css(opts.csstarget,result.style);
                   $('#' + opts.texttarget).text(result.text);
               }
            });

            $.fn.indicateStrength = function(password, options) {
                var points = $.fn.calculatePoints(password);

                var strengthArray = options.strengths;
                if (typeof(strengthArray) === 'undefined' || !strengthArray.length || points == 0) return options.blank;

                //binary search for appropriate strength rule
                var high = strengthArray.length - 1;
                var low = 0;

                while (low <= high) {
                    mid = parseInt((low + high) / 2)
                    element = strengthArray[mid][0];
                    if (element > points) {
                        high = mid - 1;
                    } else if (element < points) {
                        if (mid+2 <= strengthArray.length) { //next element still in-bounds
                            if (points < strengthArray[mid+1][0]) {
                                return strengthArray[mid][1];
                            }
                        }
                        low = mid + 1;
                    } else {
                        return strengthArray[mid][1];
                    }
                }

                return options.blank;
            };

            $.fn.calculatePoints = function(password) {
                if (password.length < 1) return 0;
                password = password.toString();

                //max number of points = 100
                var points = 0;

                var hasLetters = false,
                    hasNumbers = false,
                    hasCharacters = false,
                    hasMixedCase = false;

                //    Password Length:
                //
                //    5 Points: Less than 4 characters
                //    10 Points: 5 to 7 characters
                //    25 Points: 8 or more

                if (password.length >= 8) {
                    points+=25;
                } else if (password.length > 5){
                    points+=10;
                } else {
                    points+=5;
                }

                //   Numbers:
                //
                //    0 Points: No numbers
                //    10 Points: 1 number
                //    20 Points: 3 or more numbers

                var manyDigitRE = /\d[^\d]*\d[^\d]*\d/; //3 digits seperated by 0 or more non-digits
                var digitRE = /\d/;

                if ( password.match(digitRE) ){
                    points+=10;
                    hasNumbers = true;
                    if (password.match(manyDigitRE)) {
                        points+=10;
                    }
                }

                //    Letters:
                //
                //    0 Points: No letters
                //    10 Points: Letters are all one case
                //    20 Points: Letters are upper case and lower case

                var hasLower = (password.match(/[a-z]/) != null);
                var hasUpper = (password.match(/[A-Z]/) != null);

                if (hasLower || hasUpper) {
                    points+=10;
                    hasLetters = true;
                    if (hasLower && hasUpper) {
                        points+=10;
                        hasMixedCase = true;
                    }
                }

                //    Characters:
                //
                //    0 Points: No characters
                //    10 Points: 1 character
                //    25 Points: More than 1 character

                var characterRE = /[!@#$%^&*?_~]/;
                var manyCharacterRE = /[!@#$%^&*?_~](.*)[!@#$%^&*?_~]/;

                if (password.match(characterRE)) {
                    points +=10;
                    hasCharacters = true;
                    if (password.match(manyCharacterRE)){
                        points+=15;
                    }
                }

                //    Bonus:
                //
                //    2 Points: Letters and numbers
                //    3 Points: Letters, numbers, and characters
                //    5 Points: Mixed case letters, numbers, and characters

                if (hasLetters && hasNumbers){
                    points+=2;
                    if (hasCharacters) {
                        points+=3;
                        if (hasMixedCase) {
                            points+=5;
                        }
                    }
                }

                return points;
            };

        });
    };
})(jQuery);
