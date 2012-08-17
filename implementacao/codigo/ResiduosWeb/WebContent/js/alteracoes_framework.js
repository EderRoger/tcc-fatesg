$(document).delegate("#relatorios, #cadastros, #index, #lstUsuarios, #lstResiduos, #lstOfertaProcura, #lstIndustrias, #lstResiduos, #frmUsuario, #frmResiduo, #frmOfertaProcura","pagecreate", function() {
	$.mobile.loadingMessage = "Carregando...";
	$.mobile.pageLoadErrorMessage = "Erro ao carregar";
	$.mobile.page.prototype.options.backBtnText = "Voltar";
	$.mobile.listview.prototype.options.filterPlaceholder = "Entre com um termo para buscar";
	$.mobile.defaultPageTransition = "slide";
	$.mobile.orientationChangeEnabled = false;
});

$(document).delegate("#login","pageshow",function() {
	$.mobile.defaultPageTransition = "slideup";
	$.mobile.orientationChangeEnabled = false;
	$.mobile.loadingMessage = "Carregando...";
	$.mobile.pageLoadErrorMessage = "Erro ao carregar";
	$.mobile.page.prototype.options.backBtnText = "Voltar";
	$.mobile.listview.prototype.options.filterPlaceholder = "Entre com um termo para buscar";
});



