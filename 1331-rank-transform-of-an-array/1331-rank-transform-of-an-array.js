/**
 * @param {number[]} arr
 * @return {number[]}
 */
var arrayRankTransform = function(arr) {
   // O(n log n) time, O(n) space

    let sortedUnique = [...new Set(arr)].sort((a, b) => a - b);
    let rank = new Map();
    sortedUnique.forEach((num, index) => {
        rank.set(num, index + 1);
    });
    return arr.map(num => rank.get(num));


   

};