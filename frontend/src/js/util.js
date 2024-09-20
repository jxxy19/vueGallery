export default {
    getNumberFormatted(val) {
        return val.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
    roundHundred(val) {
        return Math.round(val / 100) * 100;
    }
}
