<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">    

</script>
	<body>
		<form>



			Code:
			<s:url id="ajax" value="ajax1.action" />
			<sj:a id="ajaxlink" href="%{ajax}" targets="result"
				indicator="indicator" button="true" buttonIcon="ui-icon-refresh"> Run AJAX Action </sj:a>
			二． Code:
			<s:form id="form" action="echo" theme="simple" cssClass="yform">
				<fieldset>
					<legend>
						AJAX Form
					</legend>
					<div class="type-text">
						<label for="echo">
							Echo:
						</label>
						<s:textfield id="echo" name="echo" value="Hello World!!!" />
					</div>
					<div class="type-button">
						<sj:submit targets="formResult" value="AJAX Submit"
							indicator="indicator" />
						<s:url id="simpleecho" value="/simpleecho.action" />
						<sj:submit href="%{simpleecho}" targets="formResult"
							value="AJAX Submit 2" indicator="indicator" />
					</div>
				</fieldset>
			</s:form>
			三． Code:
			<s:url id="ajax" value="/ajax1.action" />
			<sj:div href="%{ajax}" indicator="indicator">
				<img id="indicator" src="images/indicator.gif" alt="Loading..."
					style="display: none" />
			</sj:div>
			四． Code:
			<sj:tabbedpanel id="localtabs">
				<sj:tab id="tab1" target="tone" label="Local Tab One" />
				<sj:tab id="tab2" target="ttwo" label="Local Tab Two" />
				<sj:tab id="tab3" target="tthree" label="Local Tab Three" />
				<sj:tab id="tab4" target="tfour" label="Local Tab Four" />
				<div id="tone">
					Mauris mauris ante
				</div>
				<div id="ttwo">
					Sed non urna.
				</div>
				<div id="tthree">
					Nam enim risus, molestie et
				</div>
				<div id="tfour">
					per inceptos himenaeos.
				</div>
			</sj:tabbedpanel>
			五． Code:
			<s:form id="form" theme="xhtml">
				<sj:datepicker id="date0" label="Select a Date" />
				<sj:datepicker value="%{dateValue}" id="date1" name="date1"
					label="Date Value from Action" />
				<sj:datepicker id="date2" name="nameValue"
					label="Date Value by Name" />
				<sj:datepicker value="today" id="date3" name="date3"
					displayFormat="dd.mm.yy" label="Today" />
				<sj:datepicker value="yesterday" id="date4" name="date4"
					displayFormat="mm/dd/yy" label="Yesterday" />
				<sj:datepicker value="tomorrow" id="date5" name="date5"
					displayFormat="DD, d MM yy" label="Tomorrow" />
				<sj:datepicker value="2004-08-14" id="date6" name="date6"
					displayFormat="d M, yy" label="String Value" />
			</s:form>
			六． Code:
			<sj:dialog id="mydialog" title="Dialog with local content"> Mauris mauris ante </sj:dialog>
			七． Code:
			<s:url id="urlajax1" action="ajax1" />
			<sj:accordion id="accordion">
				<sj:accordionItem title="Mauris mauris ante">
					<sj:div id="divInAccrodionItem" href="%{urlajax1}" />
				</sj:accordionItem>
				<sj:accordionItem title="Sed non urna"> Sed non urna </sj:accordionItem>
				<sj:accordionItem title="Nam enim risus"> Nam enim risus </sj:accordionItem>
				<sj:accordionItem title="Cras dictum"> Cras dictum. </sj:accordionItem>
			</sj:accordion>
			八． Code in JSP:
			<strong>Result Div :</strong>
			<div id="result" class="result ui-widget-content ui-corner-all">
				Submit form bellow.
			</div>
			<s:form id="formAutocomplete" action="echo" theme="xhtml">
				<sj:autocompleter id="languages" name="echo" list="%{languages}"
					label="Select Languages" />
				<sj:submit id="submitFormAutocomplete" targets="result"
					button="true" validate="true" value="Submit" indicator="indicator" />
			</s:form>
			<img id="indicator" src="images/indicator.gif" alt="Loading..."
				style="display: none" />
			九． Code:
			<sj:slider id="simpleslider" />
			十． Grid A simple grid with pager. This Grid is sortable by name
			column JSPCODE
			<s:url id="remoteurl" action="jsontable" />


		</form>
	</body>
</html>
