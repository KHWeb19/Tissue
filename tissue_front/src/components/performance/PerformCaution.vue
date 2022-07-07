<template>
    <div>
        <div style="border-top: 2px solid black">
            <v-container class="mt-15 mb-10">
                <v-row v-for="item in items" :key="item.name" justify="start">
                    <v-col class="itemTitle" cols="3">
                    {{item.name}}
                    </v-col>
                    <v-col cols="9" class="content">
                        <div v-if="item.name =='예매'">
                            예매는 PC를 통해 신용카드, 가상계좌(무통장 입금)로 예매하실 수 있습니다. <br/>
                            (단, 상품마다 사용 가능한 결제 수단이 다르게 적용될 수 있으니 상품 상세페이지 안내 사항을 확인해주세요.) <br/>
                            무통장입금 결제 선택 시 입금 마감시간은 예매일 익일 밤 11시 29분까지입니다. <br/>
                            입금 마감시간 내 미입금 된 경우 예매가 자동 취소됩니다. <br/>
                            (단, 상품에 따라 예매 당일 밤 11시 29분에 마감되는 경우가 있으니 예매 전후 반드시 확인해주시기 바랍니다.) <p/>
                            예매수수료는 다음과 같습니다.<p/>
                            <table class="mt-8">
                                    <tr class="trTitle">
                                        <td>장르</td>
                                        <td>온라인 예매</td>
                                        <td>전화 예매</td>
                                    </tr>
                                    <tr>
                                        <td>콘서트, 뮤지컬</td>
                                        <td>장당 1,000원</td>
                                        <td rowspan="2">장당 2,000원</td>
                                    </tr>
                                    <tr>
                                        <td>연극, 전시회 </td>
                                        <td>장당 500원</td>
                                    </tr>
                            </table>
                        </div>
                        <div v-if="item.name =='티켓수령방법'" class="mt-5 mb-15">
                            <strong> 현장수령 </strong> <p/>
                            티켓은 예매자 본인 수령이 기본 원칙입니다. <br/>
                            공연 당일 예매확인서와 예매자의 신분증 을 지참하신 후 공연장 매표소에서 티켓을 수령하시면 됩니다. <p/>
                            <br/>
                            <strong> 배송 </strong> <p/>
                            배송을 선택하신 경우 예매완료(결제익일) 기준 5~7일 이내에 예매 시 입력하신 주소로 배송됩니다.(영업일 기준)<br/>
                            일괄배송의 경우 각 상품의 지정된 배송일자 기준으로 배송이 시작됩니다. <br/>
                            배송 시작일은 상세정보 및 예매공지사항에서 확인할 수 있습니다. <br/>
                            지역 및 배송 서비스 사정에 따라 배송사가 변경될 수 있으며, 배송일이 추가적으로 소요될 수 있습니다.<br/>
                            * 티켓 수령과 관련된 정책은 상품별로 상이하니 자세한 내용은 각 상품 상세정보에서 확인 부탁드립니다. <br/>
                        </div>
                        <div v-if="item.name =='취소마감시간'">
                            <table class="mt-5 mb-8" style="width:75%">
                                    <tr class="trTitle">
                                        <td>공연 관람일</td>
                                        <td>취소 마감일</td>
                                    </tr>
                                    <tr>
                                        <td>화요일 ~ 토요일</td>
                                        <td>관람일 전일 평일 오후 5시</td>
                                    </tr>
                                    <tr>
                                        <td>일요일 ~ 월요일 </td>
                                        <td>토요일 오전 11시</td>
                                    </tr>
                                    <tr>
                                        <td>공휴일 및 공휴일 익일 </td>
                                        <td>
                                            공휴일 전일이 평일인 경우 - 오후 5시 <br/> 
                                            공휴일 전일이 주말인 경우 - 토요일 오전 11시 <br/> 
                                            <span style="color:red"> 공휴일에는 취소 불가 </span></td>
                                    </tr>
                            </table>
                            관람일 당일 예매하신 건은 취소, 변경, 환불 불가합니다. <br/>
                            배송이 시작된 티켓의 경우 티켓이 Tissue 고객센터로 취소마감시간 이전까지 반송되어야 취소 가능합니다. <br/>
                            (취소수수료는 도착일 기준으로 부과됩니다.) <br/>
                            결제수단을 복수로 선택한 경우 인터넷으로 부분취소가 불가하오니, 고객센터로 문의해주시기 바랍니다. <p/>

                            <span style="color:red">일부 공연에 한해, 취소 마감 정책이 달라질 수 있습니다. <br/>
                            각 상품 상세페이지에 안내되어 있는 취소 정책이 우선 적용되오니, 반드시 예매 전 확인해주시기 바랍니다. </span><br/>
                        </div>
                        <div v-if="item.name =='취소수수료'">
                            <table class="mt-5 mb-8" style="width:75%">
                                    <tr class="trTitle">
                                        <td>취소일</td>
                                        <td>취소 수수료</td>
                                    </tr>
                                    <tr>
                                        <td>에매 후 7일 이내</td>
                                        <td>없음</td>
                                    </tr>
                                    <tr>
                                        <td>예매 후 8일 ~ 관람일 10일 전까지 </td>
                                        <td>장당 2,000원</td>
                                    </tr>
                                    <tr>
                                        <td>관람일 9일 전 ~ 관람일 5일 전까지 </td>
                                        <td>티켓 금액의 10%</td>
                                    </tr>
                                    <tr>
                                        <td>~ 취소 마감일시까지 </td>
                                        <td>티켓 금액의 30%</td>
                                    </tr>
                            </table>
                            예매 후 7일 이내라도 취소시점이 관람일로부터 10일 이내라면 그에 해당하는 취소수수료가 부과됩니다. <br/>
                            관람일 당일 취소 가능한 상품의 경우 관람일 당일 취소 시 티켓금액의 90%가 부과됩니다. <br/>
                            <span style="color:red">상품의 특성에 따라 취소수수료 정책이 달라질 수 있습니다. (각 상품 예매 시 취소수수료 확인) </span>
                            <br/>
                        </div>
                        <div v-if="item.name =='환불'">
                            예매수수료는 예매 당일 밤 12시 이전까지 환불되며, 그 이후 기간에는 환불되지 않습니다. <br/>
                            배송 및 반송처리 된 티켓의 배송비는 환불되지 않습니다.
                            <table class="mt-10 mb-15">
                                    <tr class="trTitle">
                                        <td>결제수단</td>
                                        <td>환불안내</td>
                                    </tr>
                                    <tr>
                                        <td>신용카드</td>
                                        <td class="subContent">일반적으로 취소완료 되고 4~5일(토, 공휴일 제외) 후 카드 승인 취소가 확인됩니다. <br/>
                                        취소시점에 따라 취소수수료와 예매수수료, 배송 받으신 경우 배송비를 제외한 금액만큼 환불 처리 됩니다.  <br/>
                                        (부분 취소 시에는 잔여 티켓 금액 + 수수료 등을 제외하고 환불 처리 됩니다.) <br/>
                                        각 카드사의 취소 정책에 따라 승인취소 및 환급일에 차이가 있을 수 있습니다. <br/>
                                        자세한 사항은 카드사로 문의 바랍니다.</td>
                                    </tr>
                                    <tr>
                                        <td>무통장 입금 </td>
                                        <td class="subContent">예매 취소 시 반드시 환불 받으실 계좌번호와 은행명을 입력하세요. <br/>
                                        취소수수료,예매수수료와 배송 받으신 경우 배송비를 제외한 후 환불 처리됩니다. <br/>
                                        취소 후 환불 처리는 영업일 기준 3~5일 정도 소요됩니다. <br/>
                                        환불진행상태는 MY공연>예매확인/취소 상세내역에서 확인할 수 있습니다. <p/>

                                        <span style="color:red"> * 배송 받은 티켓의 취소를 위해 고객센터로 발송하실 때, <br/>
                                        환불 받으실 계좌 정보를 기입하여 보내주시면 보다 빠른 환불 처리가 가능합니다. </span></td>
                                    </tr>
                                    <tr>
                                        <td>쿠폰 및 마일리지 </td>
                                        <td class="subContent">예매 취소 시 바로 복원됩니다.<br/>
                                        (단, 사용기간이 만료된 경우 복원되지 않으니 취소 전 확인해주시기 바랍니다.)</td>
                                    </tr>
                            </table>
                        </div>
                        <hr class="mt-10" style="border:0;height:1.5px;" color="black">
                    </v-col>
                </v-row>
            </v-container>
        </div>
    </div>
</template>

<script>
export default {
    name:'PerformCaution',
    data() {
        return {
            items: [
                {name:'예매'},
                {name:'티켓수령방법'},
                {name:'취소마감시간'},
                {name:'취소수수료'},
                {name:'환불'}
            ]
        }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
.itemTitle {
    font-size: 22pt;
}
.content {
font-family: 'Nanum Gothic', sans-serif !important;
    font-size:11pt;
    margin-top:10px;
}
table {
    width: 100%;
    text-align: center;
    border-collapse: collapse;
    border-spacing: 0;
}
.trTitle {
    background-color: rgb(250, 215, 221);
    font-weight: bold;
}
.subContent {
    text-align: start;
    font-size:10.5pt;
}
td {
    border-bottom: 1px solid rgb(196, 196, 196);
    padding: 12px;
}
</style>