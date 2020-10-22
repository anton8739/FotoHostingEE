regRules=document.querySelector(".regPageRules");
regRules.addEventListener('click', function () {
    console.log("rules");
    var rules=document.querySelector(".regRules");
    if(rules.style.display ==='none' || rules.style.display ===""){
        rules.style.display='block';
    }
});

closeRegRules=document.querySelector(".regRulesClose");
closeRegRules.addEventListener('click', function () {
    console.log("rulesClose");
    var rules=document.querySelector(".regRules");
    rules.style.display='none';

});
regPolicy=document.querySelector(".regPagePolicy");
regPolicy.addEventListener('click', function () {
    console.log("policy");
    var policy=document.querySelector(".regPolicy");
    if(policy.style.display ==='none' || policy.style.display ===""){
        policy.style.display='block';
    }
});

closeRegPolicy=document.querySelector(".regPolicyClose");
closeRegPolicy.addEventListener('click', function () {
    console.log("policyClose");
    var policy=document.querySelector(".regPolicy");
    policy.style.display='none';

});