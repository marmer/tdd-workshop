export function gradientChartColor(greenCenterFactor = 0,
    yellowCenterFactor = 0.5,
    redCenterFactor = 1, opacityFactor = 0.3) {
  return function (context) {
    const chart = context.chart;
    const {ctx, chartArea} = chart;

    if (!chartArea) {
      // This case happens on initial chart load
      return;
    }

    let width, height, gradient;
    const chartWidth = chartArea.right - chartArea.left;
    const chartHeight = chartArea.bottom - chartArea.top;
    if (!gradient || width !== chartWidth || height !== chartHeight) {
      // Create the gradient because this is either the first render
      // or the size of the chart has changed
      width = chartWidth;
      height = chartHeight;
      gradient = ctx.createLinearGradient(0, chartArea.bottom, 0,
          chartArea.top);
      gradient.addColorStop(greenCenterFactor,
          `rgba(0,255,0,${opacityFactor})`);
      gradient.addColorStop(yellowCenterFactor,
          `rgba(255,255,0,${opacityFactor})`);
      gradient.addColorStop(redCenterFactor, `rgba(255,0,0,${opacityFactor})`);
    }

    return gradient;
  };
}
