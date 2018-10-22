'use strict';
jQuery(document).ready(function($) {
	var SINGLETON = function() {
		// Private Properties

		// Public Properties

		// Private Method

		function showAddAuthor () {
			$('.add-author-information').click(function(event) {
				addAuthor ();
			});
		}

		function addAuthor () {
			var counting = $('.author1').siblings().length + 2;
			var authorLabel = "Author " + counting;
			var author = "author" + counting;
			var firstname = "firstName" + counting;
			var lastname = "lastName" + counting;
			var email = "email" + counting;
			var country = "country" + counting;
			var organization = "organization" + counting;
			var webpage = "webpage" + counting;
			var corresponding = "corresponding" + counting;

			var newAuthorForm = `
			<div class="author">
			<strong>${authorLabel}</strong>
			<span class="remove-author ${author}">x</span>
			<div class="author-container">
			First name(+):
			<input type="text" name="${firstname}">
			</div>
			<div class="author-container">
			Last name (*):
			<input type="text" name="${lastname}" required="required">
			</div>
			<div class="author-container">
			Email (*):
			<input type="text" name="${email}" required="required">
			</div>
			<div class="author-container">
			Country (*):
			<input type="text" name="${country}" required="required">
			</div>
			<div class="author-container">
			Organization (*):
			<input type="text" name="${organization}" required="required">
			</div>
			<div class="author-container">
			Web page:
			<input type="text" name="${webpage}">
			</div>
			<div class="author-container">
			<input type="checkbox" name="${corresponding}" value="on">
			<span>corresponding author</span>
			</div>
			</div>
			`;
			$('.author-information').append(newAuthorForm);
			$('.author-counting').val(counting);
			showDeleteAuthor();
		}

		function showStep () {
			$('.btn-select-track').click(function(event) {
				$('.select-track').hide('slow/400/fast', function() {
					
				});
				var track = $('.select-track input[type="radio"]:checked').siblings().text();
				$('.track-selected').text(track);
				$('.mark-information').show('slow/400/fast', function() {
					
				});
			});
		} 

		function showDeleteAuthor () {
			var check = false;
			$('.remove-author').click(function() {
				console.log('on delete');
				if (confirm("Delete "+$(this).siblings('strong').text()+"?")) {
					$(this).closest('.author').remove();
					check = true;
				}
				if (check) {
					var authorCouting = $('.author1').siblings().length;
					while ($('.author1').siblings().length>0) {
						$('.author1').siblings()[0].remove();
					}
					for (var i =0; i< authorCouting; i++) {
						addAuthor ();
					}
				}
			});
		}

		//Public Method
		return function ( ) {
			showAddAuthor();
			showStep ();
		}
	}	
	SINGLETON()();
});


